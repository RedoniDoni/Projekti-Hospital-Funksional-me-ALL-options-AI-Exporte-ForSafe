import React, { useEffect, useState } from "react";
import { Button, Input, Select, Form as AntForm } from "antd";
import { Formik, Form, Field } from "formik";
import * as Yup from "yup";
import { createAppointment } from "../../services/requests/reserveDoctor";
import { fetchAllDoctors } from "../../services/requests/doctor";

const { Option } = Select;

const CreateAppointmentForm = () => {
  const [doctors, setDoctors] = useState([]);

  const token = localStorage.getItem('authToken');
  const decodedToken = JSON.parse(atob(token.split('.')[1]));
  const patientId = decodedToken.userId 

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const response = await fetchAllDoctors();
        if (response && Array.isArray(response.data)) {
          setDoctors(response.data);
        } else {
          console.error("Invalid response format for doctors:", response);
          setDoctors([]);
        }
      } catch (error) {
        console.error("Error fetching doctors:", error);
        setDoctors([]);
      }
    };

    fetchDoctors();
  }, []);

  const initialValues = {
    patientId: patientId || 54,
    doctorId: "",
    status: "",
    reason: "",
  };

  const validationSchema = Yup.object({
    doctorId: Yup.string().required('Doctor is required'),
    status: Yup.string().required('Status is required'),
    reason: Yup.string().required('Reason is required'),
  });

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={async (values, { setSubmitting, resetForm }) => {
        try {
          await createAppointment(values);
          resetForm();
          console.log("Appointment created:", values);
        } catch (error) {
          console.error("Error creating appointment:", error);
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ isSubmitting, setFieldValue }) => (
        <Form className="create-appointment-form" style={{width:"50%", margin: "20px"}}>
          <Field name="doctorId">
            {() => (
              <AntForm.Item label="Doctor">
                <Select
                  placeholder="Select a doctor"
                  onChange={(value) => setFieldValue("doctorId", value)}
                >
                  {Array.isArray(doctors) &&
                    doctors.map((doctor) => (
                      <Option key={doctor.doctorId} value={doctor.doctorId}>
                        {doctor.firstName} {doctor.lastName}
                      </Option>
                    ))}
                </Select>
              </AntForm.Item>
            )}
          </Field>

          <Field name="status">
            {({ field }) => (
              <AntForm.Item label="Status">
                <Select
                  {...field}
                  placeholder="Select status"
                  onChange={(value) => setFieldValue("status", value)}
                >
                  <Option value="ACTIVE">Active</Option>
                  <Option value="INACTIVE">Inactive</Option>
                  <Option value="DELETED">Deleted</Option>
                </Select>
              </AntForm.Item>
            )}
          </Field>

          <Field name="reason">
            {({ field }) => (
              <AntForm.Item label="Reason">
                <Input {...field} placeholder="Reason" />
              </AntForm.Item>
            )}
          </Field>

          <Button type="primary" htmlType="submit" loading={isSubmitting}>
            Create Appointment
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default CreateAppointmentForm;
