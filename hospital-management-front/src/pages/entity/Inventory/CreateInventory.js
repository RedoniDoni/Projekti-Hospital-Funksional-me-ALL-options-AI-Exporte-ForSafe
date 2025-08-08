import { Button, Input } from 'antd';
import { Field, Form, Formik } from 'formik';
import React from 'react';
import * as Yup from 'yup';
import { createInventory } from '../../../services/requests/inventory';

const CreateInventory = ({ onCreateComplete }) => {
  const initialValues = {
    article: '',
    description: '',
    quantity: '',
    price: ''
  };

  const validationSchema = Yup.object({
    article: Yup.string().required('Article name is required'),
  });

  return (
    <div className="create-inventory-form">
      <h2>Create Inventory</h2>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={async (values, { setSubmitting, resetForm }) => {
          try {
            await createInventory(values);
            resetForm();
            onCreateComplete();
          } catch (error) {
            console.error('Error creating inventory:', error);
          } finally {
            setSubmitting(false);
          }
        }}
      >
        {({ isSubmitting, errors, touched }) => (
          <Form className="create-user-form" layout="vertical">
            <Field name="article">
              {({ field }) => <Input {...field} placeholder="Article" />}
            </Field>
            <Field name="description">
              {({ field }) => <Input {...field} placeholder="Description" />}
            </Field>
            <Field name="quantity">
              {({ field }) => <Input {...field} placeholder="Quantity" />}
            </Field>
            <Field name="price">
              {({ field }) => <Input {...field} placeholder="Price" />}
            </Field>
            <Button type="primary" htmlType="submit" loading={isSubmitting}>
              {isSubmitting ? 'Saving...' : 'Save Inventory'}
            </Button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default CreateInventory;
