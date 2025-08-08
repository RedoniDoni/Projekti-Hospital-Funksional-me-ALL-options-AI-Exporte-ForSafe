import React from "react";
import "./home.css";

const Contact = () => {
  return (
    <div>
      <h1>Contact Us.</h1>

      <footer className="contact-footer">
        <div className="contact-container">
          <div className="contact-content">
            <p>
              <strong>Email:</strong>{" "}
              <a href="mailto:admin@admin.com">admin@admin.com</a>
            </p>
            <p>
              <strong>Phone:</strong> <a href="tel:+201234567">01234567</a>
            </p>
            <p>
              <strong>Address:</strong> 1234 Main St, Sample City, Country
            </p>
          </div>
          <div className="map-container">
            <iframe
              title="Google Map"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3153.797707956688!2d144.96328031580452!3d-37.81362727975135!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6ad65d42bfcff207%3A0xd99313f7a06320b7!2sFederation%20Square!5e0!3m2!1sen!2sus!4v1615972920478!5m2!1sen!2sus"
              width="100%"
              height="100%"
              style={{ border: "0", borderRadius: "8px" }}
              allowFullScreen=""
              loading="lazy"
            ></iframe>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Contact;
