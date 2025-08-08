import { Button, Input, Modal } from 'antd';
import { Field, Form, Formik } from 'formik';
import React from 'react';
import * as Yup from 'yup';
import { registerUser } from '../../services/requests/auth/auth';

const CreateUserModal = ({ visible, onCancel, onCreate }) => {
  const initialValues = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phoneNumber: '',
    imageUrl: '',
    role: 'Doctor',
  };

  const validationSchema = Yup.object({
    firstName: Yup.string().required('First name is required'),
    lastName: Yup.string().required('Last name is required'),
    email: Yup.string().email('Enter a valid email').required('Email is required'),
    password: Yup.string().min(5, 'Password must be at least 5 characters').required('Password is required'),
  });

  return (
    <Modal title="Create Doctor" visible={visible} onCancel={onCancel} footer={null}>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={async (values, { setSubmitting, resetForm }) => {
          try {
            await registerUser(values);
            resetForm();
            onCreate(values);
          } catch (error) {
            console.error('Error creating user:', error);
          } finally {
            setSubmitting(false);
          }
        }}
      >
        {({ isSubmitting }) => (
          <Form className="create-user-form" layout="vertical">
            <Field name="firstName">
              {({ field }) => <Input {...field} placeholder="First Name" />}
            </Field>
            <Field name="lastName">
              {({ field }) => <Input {...field} placeholder="Last Name" />}
            </Field>
            <Field name="email">
              {({ field }) => <Input {...field} placeholder="Email" />}
            </Field>
            <Field name="password">
              {({ field }) => <Input.Password {...field} placeholder="Password" />}
            </Field>
            <Field name="phoneNumber">
              {({ field }) => <Input {...field} placeholder="Phone Number" />}
            </Field>
            <Field name="role">
              {({ field }) => <Input {...field} placeholder={initialValues.role || "Role"} />}
            </Field>
            <Button type="primary" htmlType="submit" loading={isSubmitting}>
              Save
            </Button>
            .
          </Form>
        )}
      </Formik>
    </Modal>
  );
};

export default CreateUserModal;
