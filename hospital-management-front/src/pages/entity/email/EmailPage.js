import React, { useState } from 'react';
import { sendEmail } from '../../../services/requests/email';
import './emailPage.scss';

const EmailPage = () => {
  const [emailData, setEmailData] = useState({
    to: '',
    subject: '',
    body: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmailData({
      ...emailData,
      [name]: value,
    });
  };

  const handleSend = () => {
    if (!emailData.to || !emailData.subject || !emailData.body) {
      alert('Please fill all fields before sending.');
      return;
    }

    sendEmail(emailData)
      .then((response) => {
        console.log('Email sent successfully:', response);
        alert('Email sent successfully!');
        setEmailData({
          to: '',
          subject: '',
          body: '',
        });
      })
      .catch((error) => {
        console.error('Failed to send email:', error);
        alert('Failed to send email.');
      });
  };

  return (
    <div className="email-page">
      <h2>Send Email</h2>
      <div className="form-group">
        <label>To</label>
        <input
          type="email"
          name="to"
          value={emailData.to}
          onChange={handleChange}
          placeholder="Enter recipient email"
        />
      </div>
      <div className="form-group">
        <label>Subject</label>
        <input
          type="text"
          name="subject"
          value={emailData.subject}
          onChange={handleChange}
          placeholder="Enter email subject"
        />
      </div>
      <div className="form-group">
        <label>Body</label>
        <textarea
          name="body"
          value={emailData.body}
          onChange={handleChange}
          placeholder="Enter email body"
        />
      </div>
      <button className="send-button" onClick={handleSend}>
        Send Email
      </button>
    </div>
  );
};

export default EmailPage;
