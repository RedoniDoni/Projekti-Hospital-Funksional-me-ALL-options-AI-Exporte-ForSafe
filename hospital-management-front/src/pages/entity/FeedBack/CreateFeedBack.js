import { Button, Input } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import * as Yup from "yup";
import { createFeedBack } from "../../../services/requests/feedBack";
import { fetchAllNurses } from "../../../services/requests/nurse";

const CreateFeedBack = ({ onCreateComplete }) => {
  const [nurses, setNurses] = useState([]);
  const [userId, setUserId] = useState("");

  const initialValues = {
    comment: "",
    rating: "",
    nurseId: "",
    userId: "", // Set userId to be included in the feedback data
  };

  const validationSchema = Yup.object({
    comment: Yup.string().required("Your comment is required"),
    rating: Yup.number()
      .min(1, "Rating must be at least 1")
      .max(5, "Rating must be at most 5")
      .required("Rating is required"),
    nurseId: Yup.string().notRequired(),
  });

  // Fetch userId from the token and nurses list
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

    // Fetch nurses
    const fetchNurses = async () => {
      try {
        const response = await fetchAllNurses();
        if (response && Array.isArray(response)) {
          setNurses(response); // Assuming response is an array of nurses
        } else {
          console.error("Unexpected response format:", response);
        }
      } catch (error) {
        console.error("Error fetching nurses:", error);
      }
    };

    fetchNurses();
  }, []);

  return (
    <div className="create-feedback-form">
      <h2>Create Feedback</h2>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={async (values, { setSubmitting, resetForm }) => {
          try {
            // Dynamically update the userId when it changes
            values.userId = userId;

            await createFeedBack(values); // Send nurseId and userId in the payload
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

            <Field name="nurseId">
              {({ field }) => (
                <div>
                  <label>Nurse</label>
                  <select
                    {...field}
                    className="nurse-dropdown"
                    onChange={(e) => setFieldValue("nurseId", e.target.value)}
                  >
                    <option value="">Select a Nurse</option>
                    {nurses.map((nurse) => (
                      <option key={nurse.id} value={nurse.id}>
                        {nurse.firstName} {nurse.lastName}
                      </option>
                    ))}
                  </select>
                  {errors.nurseId && touched.nurseId && (
                    <div className="error">{errors.nurseId}</div>
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
