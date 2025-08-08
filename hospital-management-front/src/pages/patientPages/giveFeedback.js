import { Form, Input, Modal, Button, notification } from "antd";
import React, { useEffect, useState } from "react";
import { fetchAllFeedBacks, createFeedBack } from "../../services/requests/feedBack";

const SeeDoctorsAndNurses = () => {
  const [feedBacks, setFeedBacks] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [form] = Form.useForm();

  // Fetch feedbacks on component mount
  useEffect(() => {
    const fetchFeedbacks = async () => {
      try {
        const response = await fetchAllFeedBacks();
        setFeedBacks(response.data); // Ensure accessing the correct data structure
      } catch (error) {
        console.error("Error fetching feedbacks:", error);
        notification.error({
          message: "Error",
          description: "Failed to fetch feedbacks. Please try again later.",
        });
      }
    };
    fetchFeedbacks();
  }, []);

  const handleSubmitFeedback = async () => {
    try {
      const feedbackData = await form.validateFields(); // Validate form data
      await createFeedBack(feedbackData); // Submit feedback to the server
      notification.success({
        message: "Success",
        description: "Feedback submitted successfully.",
      });

      // Refresh feedback list after submission
      setFeedBacks((prev) => [...prev, feedbackData]);

      setIsModalVisible(false); // Close modal
      form.resetFields(); // Reset form fields
    } catch (error) {
      console.error("Error creating feedback:", error);
      notification.error({
        message: "Error",
        description: "Failed to submit feedback. Please try again later.",
      });
    }
  };

  const handleCancel = () => {
    setIsModalVisible(false);
    form.resetFields();
  };

  const openFeedbackModal = () => {
    setIsModalVisible(true);
  };

  return (
    <div className="see-doctors-nurses">
      <h2>All Feedbacks</h2>
      <Button type="primary" onClick={openFeedbackModal}>
        Give Feedback
      </Button>

      <table className="feedback-table">
        <thead>
          <tr>
            <th>Comment</th>
            <th>Rating</th>
            <th>Doctor ID</th>
            <th>Nurse ID</th>
          </tr>
        </thead>
        <tbody>
          {feedBacks.map((feedback, index) => (
            <tr key={index}>
              <td>{feedback.comment}</td>
              <td>{feedback.rating}</td>
              <td>{feedback.doctorId || "N/A"}</td>
              <td>{feedback.nurseId || "N/A"}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <Modal
        title="Give Feedback"
        visible={isModalVisible}
        onOk={handleSubmitFeedback}
        onCancel={handleCancel}
        okText="Submit"
        cancelText="Cancel"
      >
        <Form form={form} layout="vertical">
          <Form.Item
            name="comment"
            label="Comment"
            rules={[{ required: true, message: "Please provide your comment" }]}
          >
            <Input.TextArea placeholder="Write your feedback" />
          </Form.Item>
          <Form.Item
            name="rating"
            label="Rating"
            rules={[
              { required: true, message: "Please provide a rating" },
              { type: "number", min: 1, max: 5, message: "Rating must be between 1 and 5" },
            ]}
          >
            <Input type="number" placeholder="Rating (1-5)" min={1} max={5} />
          </Form.Item>
          <Form.Item
            name="doctorId"
            label="Doctor ID"
            rules={[{ required: false, message: "Provide doctor ID if applicable" }]}
          >
            <Input placeholder="Doctor ID (optional)" />
          </Form.Item>
          <Form.Item
            name="nurseId"
            label="Nurse ID"
            rules={[{ required: false, message: "Provide nurse ID if applicable" }]}
          >
            <Input placeholder="Nurse ID (optional)" />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default SeeDoctorsAndNurses;
