import { Button, Input, Select } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import * as Yup from "yup";
import { fetchAllDoctors } from "../../../services/requests/doctor"; // Fetch function for doctors
import { createFeedBack } from "../../../services/requests/feedBack";

const CreateFeedBack = ({ onCreateComplete }) => {
  const [doctors, setDoctors] = useState([]);
  const [userId, setUserId] = useState("");

  useEffect(() => {
    // Fetch userId from token
    const decodeToken = () => {
      const token = localStorage.getItem("authToken");
      if (token) {
        try {
          const decodedToken = JSON.parse(atob(token.split(".")[1]));
          setUserId(decodedToken?.userId || ""); // Set userId from token
        } catch (error) {
          console.error("Error decoding token:", error);
        }
      }
    };

    decodeToken();

    // Fetch doctors
    const fetchDoctors = async () => {
      try {
        const response = await fetchAllDoctors();
        if (response && response.status === 200 && Array.isArray(response.data)) {
          setDoctors(response.data); // Set the `data` array to the `doctors` state
        } else {
          console.error("Unexpected response format:", response);
        }
      } catch (error) {
        console.error("Error fetching doctors:", error);
      }
    };

    fetchDoctors();
  }, []);

  const initialValues = {
    comment: "",
    rating: "",
    doctorId: "",
    userId: userId, // Dynamically set from state
  };

  const validationSchema = Yup.object({
    comment: Yup.string().required("Your comment is required"),
    rating: Yup.number()
      .min(1, "Rating must be at least 1")
      .max(5, "Rating must be at most 5")
      .required("Rating is required"),
    doctorId: Yup.string().notRequired(),
  });

  return (
    <div className="create-feedback-form">
      <h2>Create Feedback</h2>
      <Formik
        enableReinitialize // Allow reinitialization when initialValues change
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={async (values, { setSubmitting, resetForm }) => {
          try {
            await createFeedBack(values); // Send doctorId and userId in the payload
            resetForm();
            onCreateComplete();
          } catch (error) {
            console.error("Error creating feedback:", error);
          } finally {
            setSubmitting(false);
          }
        }}
      >
        {({ isSubmitting, errors, touched, setFieldValue }) => (
          <Form className="create-user-form" layout="vertical">
            <Field name="comment">
              {({ field }) => (
                <div>
                  <label>Comment</label>
                  <Input {...field} placeholder="Your comment" />
                  {errors.comment && touched.comment && (
                    <div className="error">{errors.comment}</div>
                  )}
                </div>
              )}
            </Field>

            <Field name="rating">
              {({ field }) => (
                <div>
                  <label>Rating</label>
                  <Input
                    {...field}
                    type="number"
                    placeholder="Rating (1-5)"
                    min={1}
                    max={5}
                  />
                  {errors.rating && touched.rating && (
                    <div className="error">{errors.rating}</div>
                  )}
                </div>
              )}
            </Field>

            <Field name="doctorId">
              {({ field }) => (
                <div>
                  <label>Doctor</label>
                  <Select
                    {...field}
                    placeholder="Select Doctor"
                    onChange={(value) => setFieldValue("doctorId", value)}
                  >
                    {doctors && doctors.length > 0 ? (
                      doctors.map((doctor) => (
                        <Select.Option key={doctor.doctorId} value={doctor.doctorId}>
                          {`${doctor.firstName} ${doctor.lastName}`}
                        </Select.Option>
                      ))
                    ) : (
                      <Select.Option value={""}>Select Doctor</Select.Option>
                    )}
                  </Select>
                  {errors.doctorId && touched.doctorId && (
                    <div className="error">{errors.doctorId}</div>
                  )}
                </div>
              )}
            </Field>

            <Button type="primary" htmlType="submit" loading={isSubmitting}>
              {isSubmitting ? "Saving..." : "Save Feedback"}
            </Button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default CreateFeedBack;
