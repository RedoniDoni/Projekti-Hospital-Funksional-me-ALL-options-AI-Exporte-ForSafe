import { Button, DatePicker, Form, Input, Modal } from "antd";
import React, { useEffect, useState } from "react";
import { createVacation } from "../../../services/requests/vacation";

const CreateVacationForm = ({ onCreateComplete }) => {
  const [doctorId, setDoctorId] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);

  useEffect(() => {
    // Decode token to get userId and set it as doctorId
    const decodeToken = () => {
      const token = localStorage.getItem("authToken");
      if (token) {
        try {
          const decodedToken = JSON.parse(atob(token.split(".")[1]));
          setDoctorId(decodedToken?.userId || ""); // Use userId as doctorId
        } catch (error) {
          console.error("Error decoding token:", error);
        }
      }
    };

    decodeToken();
  }, []);

  const onFinish = async (values) => {
    try {
      const payload = {
        ...values,
        doctorId, // Include doctorId from userId in token
      };
      console.log("Payload:", payload); // Log payload for debugging
      await createVacation(payload);
      setIsModalVisible(true); // Show success modal
      onCreateComplete();
    } catch (error) {
      console.error("Error creating vacation:", error);
    }
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  return (
    <>
      <Form
        layout="vertical"
        onFinish={onFinish}
        initialValues={{
          startDate: null,
          endDate: null,
          reason: "",
          certification: "",
        }}
      >
        <Form.Item
          label="Start Date"
          name="startDate"
          rules={[{ required: true, message: "Please select a start date!" }]}
        >
          <DatePicker style={{ width: "100%" }} />
        </Form.Item>

        <Form.Item
          label="End Date"
          name="endDate"
          rules={[{ required: true, message: "Please select an end date!" }]}
        >
          <DatePicker style={{ width: "100%" }} />
        </Form.Item>

        <Form.Item
          label="Reason"
          name="reason"
          rules={[{ required: true, message: "Please enter a reason!" }]}
        >
          <Input.TextArea rows={4} placeholder="Enter the reason for vacation" />
        </Form.Item>

        <Form.Item
          label="Certification"
          name="certification"
          rules={[{ required: true, message: "Please upload a certification!" }]}
        >
          <Input placeholder="Enter certification details" />
        </Form.Item>

        <Button type="primary" htmlType="submit">
          Submit Vacation
        </Button>
      </Form>

      <Modal
        title="Vacation Request Submitted"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleOk}
      >
        <p>Do ju kontaktojmë me email për aprovimin e pushimit.</p>
      </Modal>
    </>
  );
};

export default CreateVacationForm;
