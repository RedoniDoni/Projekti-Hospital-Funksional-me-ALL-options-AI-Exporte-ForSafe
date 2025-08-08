import { Button, Input } from 'antd';
import { Field, Form, Formik } from 'formik';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import * as Yup from 'yup';
import { editInventory, fetchInventoryById } from '../../../services/requests/inventory';

const EditInventory = () => {
  const [inventory, setInventory] = useState(null);
  const { id } = useParams(); 
  const navigate = useNavigate();

  // Fetch the inventory item by ID
  useEffect(() => {
    const fetchInventoryDetails = async () => {
      try {
        const data = await fetchInventoryById(id);
        setInventory(data.data);
      } catch (error) {
        console.error('Error fetching inventory item:', error);
      }
    };

    fetchInventoryDetails();
  }, [id]);

  const handleSubmit = async (values, { setSubmitting }) => {
    try {
      await editInventory(id, values); 
      navigate('/admin/allInventory'); 
    } catch (error) {
      console.error('Error updating inventory:', error);
    } finally {
      setSubmitting(false);
    }
  };

  const validationSchema = Yup.object({
    article: Yup.string().required('Article name is required'),
    description: Yup.string().required('Description is required'),
    quantity: Yup.number().required('Quantity is required').min(0, 'Quantity cannot be negative'),
    price: Yup.number().required('Price is required').min(0, 'Price cannot be negative'),
  });

  if (!inventory) {
    return <div>Loading...</div>; 
  }

  return (
    <div className="edit-inventory">
      <h2>Edit Inventory Item</h2>
      <Formik
        initialValues={{
          article: inventory.article,
          description: inventory.description,
          quantity: inventory.quantity,
          price: inventory.totalPrice,
        }}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        {({ isSubmitting, errors, touched }) => (
          <Form className="inventory-form" layout="vertical">
            <Field name="article">
              {({ field }) => (
                <Input {...field} placeholder="Article" />
              )}
            </Field>
            {errors.article && touched.article && <div>{errors.article}</div>}

            <Field name="description">
              {({ field }) => (
                <Input {...field} placeholder="Description" />
              )}
            </Field>
            {errors.description && touched.description && <div>{errors.description}</div>}

            <Field name="quantity">
              {({ field }) => (
                <Input {...field} placeholder="Quantity" type="number" />
              )}
            </Field>
            {errors.quantity && touched.quantity && <div>{errors.quantity}</div>}

            <Field name="price">
              {({ field }) => (
                <Input {...field} placeholder="Price" type="number" />
              )}
            </Field>
            {errors.price && touched.price && <div>{errors.price}</div>}

            <Button type="primary" htmlType="submit" loading={isSubmitting}>
              {isSubmitting ? 'Updating...' : 'Update Inventory'}
            </Button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default EditInventory;
