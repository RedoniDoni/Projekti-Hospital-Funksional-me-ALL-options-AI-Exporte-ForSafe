import { Button, Input, Modal } from "antd";
import { Field, Form, Formik } from "formik";
import React from "react";
import * as Yup from "yup";
import { createCity } from "../../../services/requests/city";

const CreateCity = ({onCreateComplete }) => {
  const initialValues = {
    name: "",
  };

  const validationSchema = Yup.object({
    name: Yup.string().required("City name is required"),
  });

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={async (values, { setSubmitting, resetForm }) => {
        try {
          await createCity(values);
          resetForm();
          onCreateComplete();
        } catch (error) {
          console.error("Error creating room:", error);
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form className="create-user-form" layout="vertical">
          <Field name="name">
            {({ field }) => (
              <div className="form-group">
                <Input {...field} placeholder="City Name" />
                {errors.name && touched.name && <div>{errors.name}</div>}
              </div>
            )}
          </Field>
          <Button type="primary" htmlType="submit" loading={isSubmitting}>
            {isSubmitting ? "Creating..." : "Create City"}
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default CreateCity;
