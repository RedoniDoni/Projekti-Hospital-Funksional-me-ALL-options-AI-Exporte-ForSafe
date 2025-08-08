import React, { useState } from "react";
import { useParams, useNavigate, useLocation } from "react-router-dom";
import { Formik, Form, Field } from "formik";
import * as Yup from "yup";
import { Input, Button } from "antd";
import { createDiagnosis } from "../../../services/requests/diagnosis";

const { TextArea } = Input;

const CreateDiagnosis = () => {
  const { appointmentId } = useParams();
  const navigate = useNavigate();
  const { state } = useLocation();
  const { patientId } = state || {};
  const [error, setError] = useState(null);

  const initialValues = {
    diagnosisDetails: "",
    treatmentPlan: "",
  };

  const validationSchema = Yup.object({
    diagnosisDetails: Yup.string().required("Diagnosis details are required"),
    treatmentPlan: Yup.string().required("Treatment plan is required"),
  });

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={async (values, { setSubmitting, resetForm }) => {
        try {
          const token = localStorage.getItem("authToken");
          const decodedToken = JSON.parse(atob(token.split(".")[1]));
          const doctorId = decodedToken?.userId;

          const diagnosisData = {
            appointmentId: Number(appointmentId),
            doctorId,
            patientId,
            ...values,
          };

          await createDiagnosis(diagnosisData);
          resetForm();
          navigate("/doctor/appointments");
        } catch (err) {
          console.error("Error creating diagnosis:", err);
          setError("Failed to create diagnosis. Please try again.");
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ isSubmitting }) => (
       <Form class="create-diagnosis-form">
       <h1 class="form-heading">Create Diagnosis</h1>
       {error && <div class="error-message">{error}</div>}
       <Field name="diagnosisDetails">
         {({ field }) => (
           <div class="form-field">
             <label class="form-label">Diagnosis Details:</label>
             <TextArea
               {...field}
               rows={4}
               placeholder="Enter diagnosis details"
               className="form-input"
             />
           </div>
         )}
       </Field>
       <Field name="treatmentPlan">
         {({ field }) => (
           <div class="form-field">
             <label class="form-label">Treatment Plan:</label>
             <TextArea
               {...field}
               rows={4}
               placeholder="Enter treatment plan"
               className="form-input"
             />
           </div>
         )}
       </Field>
       <Button type="primary" htmlType="submit" loading={isSubmitting} className="submit-btn">
         Submit
       </Button>
     </Form>
     
      )}
    </Formik>
  );
};

export default CreateDiagnosis;
