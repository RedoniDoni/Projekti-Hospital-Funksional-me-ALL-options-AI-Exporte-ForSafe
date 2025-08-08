import { Form, Input, Modal, Select } from "antd";
import React, { useEffect, useState } from "react";
import {
    fetchAllDoctors,
    updateDoctor
} from "../../services/requests/doctor";

const SeeDoctors = () => {
  const [doctors, setDoctors] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedDoctor] = useState(null);
  const [form] = Form.useForm();

  useEffect(() => {
    const fetchDoctors = async () => {
      const response = await fetchAllDoctors();
      setDoctors(response.data);
    };
    fetchDoctors();
  }, []);

  const handleOk = async () => {
    try {
      const updatedDoctor = await form.validateFields();
      await updateDoctor(selectedDoctor.id, updatedDoctor);
      setIsModalVisible(false);
      setDoctors(
        doctors.map((doctor) =>
          doctor.id === selectedDoctor.id
            ? { ...doctor, ...updatedDoctor }
            : doctor
        )
      );
    } catch (error) {
      console.error("Error updating doctor", error);
    }
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <div className="show-doctor">
      <h2>All Doctors</h2>
      <table className="doctor-table">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Specialization</th>
            <th>Department Name</th>
            <th>City Name</th>
          </tr>
        </thead>
        <tbody>
          {doctors.map((doctor, index) => (
            <tr key={index}>
              <td>{doctor.firstName}</td>
              <td>{doctor.lastName}</td>
              <td>{doctor.gender}</td>
              <td>{doctor.specialization}</td>
              <td>{doctor.departamentName}</td>
              <td>{doctor.cityName}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <Modal
        title="Edit Doctor"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        okText="Save"
        cancelText="Cancel"
      >
        <Form form={form} layout="vertical" name="editDoctorForm">
          <Form.Item
            name="firstName"
            label="First Name"
            rules={[
              { required: true, message: "Please input the first name!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="lastName"
            label="Last Name"
            rules={[{ required: true, message: "Please input the last name!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="age"
            label="Age"
            rules={[{ required: true, message: "Please input the age!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="gender"
            label="Gender"
            rules={[{ required: true, message: "Please select the gender!" }]}
          >
            <Select>
              <Select.Option value="Male">Male</Select.Option>
              <Select.Option value="Female">Female</Select.Option>
              <Select.Option value="Other">Other</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item
            name="phoneNumber"
            label="Phone Number"
            rules={[
              { required: true, message: "Please input the phone number!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="specialization"
            label="Specialization"
            rules={[
              { required: true, message: "Please input the specialization!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="qualification"
            label="Qualification"
            rules={[
              { required: true, message: "Please input the qualification!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item name="isActive" label="Is Active" valuePropName="checked">
            <Input type="checkbox" />
          </Form.Item>
          <Form.Item
            name="departamentName"
            label="Department Name"
            rules={[
              { required: true, message: "Please input the department name!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="cityName"
            label="City Name"
            rules={[{ required: true, message: "Please input the city name!" }]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default SeeDoctors;
