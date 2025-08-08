import api from "../axios";

export const sendEmail = async (emailData) => {
  try {
    const response = await api.post('/email/sendEmail', emailData);
    return response.data;
  } catch (error) {
    console.error('Error sending email:', error);
    throw error;
  }
};
