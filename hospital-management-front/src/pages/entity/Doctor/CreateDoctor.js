import { Button, Input, Modal, notification, Select } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import * as Yup from "yup";
import { fetchAllDepartments } from "../../../services/requests/department";
import { fetchAllCities } from "../../../services/requests/city";
import "./doctor.scss";
import { registerUser } from "../../../services/requests/auth";

const { Option } = Select;

const CreateDoctor = ({ onCreateComplete, initialData }) => {
  const [departments, setDepartments] = useState([]);
  const [cities, setCities] = useState([]);

  useEffect(() => {
    const loadDepartments = async () => {
      try {
        const response = await fetchAllDepartments();
        if (response && Array.isArray(response.data)) {
          setDepartments(response.data);
        } else {
          console.error("Invalid response format for departments:", response);
          setDepartments([]);
        }
      } catch (error) {
        console.error("Error fetching departments:", error);
        setDepartments([]);
      }
    };

    const loadCities = async () => {
      try {
        const response = await fetchAllCities();
        console.log("respo", response);
        if (response && Array.isArray(response.data)) {
          setCities(response.data);
        } else {
          console.error("Invalid response format for cities:", response);
          setCities([]);
        }
      } catch (error) {
        console.error("Error fetching cities:", error);
        setCities([]);
      }
    };

    loadDepartments();
    loadCities();
  }, []);

  const initialValues = {
    firstName: initialData?.firstName || "",
    lastName: initialData?.lastName || "",
    age: "",
    gender: "male",
    phoneNumber: initialData?.phoneNumber || "",
    specialization: "",
    qualification: "",
    email: initialData?.email || "",
    departmentId: "",
    cityId: "",
    password: "",
  };

  const validationSchema = Yup.object({
    firstName: Yup.string().required("First name is required"),
    lastName: Yup.string().required("Last name is required"),
    age: Yup.number()
      .required("Age is required")
      .typeError("Enter a valid age"),
    departmentId: Yup.string().required("Department is required"),
    cityId: Yup.string().required("City is required"),
    password: Yup.string()
      .min(5, "Password must be at least 5 characters")
      .required("Password is required"),
  });

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={async (values, { setSubmitting, resetForm }) => {
        try {
          await registerUser({ ...values, role: "DOCTOR" });
          resetForm();
          onCreateComplete();
        } catch (error) {
          notification.error({
            message: 'Error Creating Doctor',
            description: error.response?.data?.message || 'An unexpected error occurred',
          });
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ isSubmitting, setFieldValue }) => (
        <Form className="create-user-form" layout="vertical">
          <Field name="firstName">
            {({ field }) => <Input {...field} placeholder="First Name" />}
          </Field>
          <Field name="lastName">
            {({ field }) => <Input {...field} placeholder="Last Name" />}
          </Field>
          <Field name="age">
            {({ field }) => (
              <Input {...field} placeholder="Age" type="number" />
            )}
          </Field>
          <Field name="gender">
            {({ field }) => (
              <Select {...field} placeholder="Gender">
                <Option value="male">Male</Option>
                <Option value="female">Female</Option>
              </Select>
            )}
          </Field>
          <Field name="phoneNumber">
            {({ field }) => <Input {...field} placeholder="Phone Number" />}
          </Field>
          <Field name="specialization">
            {({ field }) => <Input {...field} placeholder="Specialization" />}
          </Field>
          <Field name="qualification">
            {({ field }) => <Input {...field} placeholder="Qualification" />}
          </Field>
          <Field name="email">
            {({ field }) => (
              <Input {...field} placeholder="Email" type="email" />
            )}
          </Field>
          <Field name="password">
            {({ field }) => (
              <Input {...field} placeholder="Password" type="password" />
            )}
          </Field>
          <Field name="departmentId">
            {() => (
              <Select
                placeholder="Select Department"
                onChange={(value) => setFieldValue("departmentId", value)}
              >
                {Array.isArray(departments) &&
                  departments.map((dept) => (
                    <Option key={dept.id} value={dept.id}>
                      {dept.departamentName}
                    </Option>
                  ))}
              </Select>
            )}
          </Field>
          <Field name="cityId">
            {() => (
              <Select
                placeholder="Select City"
                onChange={(value) => setFieldValue("cityId", value)}
              >
                {Array.isArray(cities) &&
                  cities.map((city) => (
                    <Option key={city.id} value={city.id}>
                      {city.name}
                    </Option>
                  ))}
              </Select>
            )}
          </Field>
          <Button type="primary" htmlType="submit" loading={isSubmitting}>
            Create Doctor
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default CreateDoctor;
