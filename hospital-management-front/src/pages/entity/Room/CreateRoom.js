// import { Button, Input, Modal } from 'antd';
// import { Field, Form, Formik } from 'formik';
// import React from 'react';
// import * as Yup from 'yup';
// import { createRoom } from '../../../services/requests/rooms';
// import './createRoom.scss';

// const CreateRoomModal = ({ visible, onCancel, onCreateComplete }) => {
//   const initialValues = {
//     roomName: '',
//     description: '',
//     floor: '',
//     nrOfBeds: '',
//     departamentId: '',
//   };

//   const validationSchema = Yup.object({
//     roomName: Yup.string().required('Room name is required'),
//     description: Yup.string().required('Description is required'),
//     floor: Yup.number().required('Floor is required').typeError('Enter a valid floor number'),
//     nrOfBeds: Yup.number().required('Number of beds is required').typeError('Enter a valid number'),
//     departamentId: Yup.number().required('Department ID is required').typeError('Enter a valid department ID'),
//   });

//   return (
//     <Modal
//       title="Create Room"
//       visible={visible}
//       onCancel={onCancel}
//       footer={null}
//       className='modal-title'
//     >
//       <Formik
//         initialValues={initialValues}
//         validationSchema={validationSchema}
//         onSubmit={async (values, { setSubmitting, resetForm }) => {
//           try {
//             await createRoom(values);
//             resetForm();
//             onCreateComplete();
//           } catch (error) {
//             console.error('Error creating room:', error);
//           } finally {
//             setSubmitting(false);
//           }
//         }}
//       >
//         {({ isSubmitting, errors, touched }) => (
//           <Form className="create-user-form" layout="vertical">
//             <Field name="roomName">
//               {({ field }) => (
//                 <div className="form-group">
//                   <Input {...field} placeholder="Room Name" />
//                   {errors.roomName && touched.roomName && <div>{errors.roomName}</div>}
//                 </div>
//               )}
//             </Field>
//             <Field name="description">
//               {({ field }) => (
//                 <div className="form-group">
//                   <Input {...field} placeholder="Description" />
//                   {errors.description && touched.description && <div>{errors.description}</div>}
//                 </div>
//               )}
//             </Field>
//             <Field name="floor">
//               {({ field }) => (
//                 <div className="form-group">
//                   <Input {...field} placeholder="Floor" type="number" />
//                   {errors.floor && touched.floor && <div>{errors.floor}</div>}
//                 </div>
//               )}
//             </Field>
//             <Field name="nrOfBeds">
//               {({ field }) => (
//                 <div className="form-group">
//                   <Input {...field} placeholder="Number of Beds" type="number" />
//                   {errors.nrOfBeds && touched.nrOfBeds && <div>{errors.nrOfBeds}</div>}
//                 </div>
//               )}
//             </Field>
//             <Field name="departamentId">
//               {({ field }) => (
//                 <div className="form-group">
//                   <Input {...field} placeholder="Department ID" type="number" />
//                   {errors.departamentId && touched.departamentId && <div>{errors.departamentId}</div>}
//                 </div>
//               )}
//             </Field>
//             <Button type="primary" htmlType="submit" loading={isSubmitting}>
//               {isSubmitting ? 'Saving...' : 'Save Room'}
//             </Button>
//           </Form>
//         )}
//       </Formik>
//     </Modal>
//   );
// };

// export default CreateRoomModal;
