import React from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Badge from 'react-bootstrap/Badge';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEnvelope, faPhone, faBuilding } from '@fortawesome/free-solid-svg-icons';
import './EmployeeCard.css'; 

const EmployeeCard = ({ employee, onUpdate, onDelete }) => {
  return (
    <Card className="mb-3 employee-card shadow-sm">
      <Card.Body>
        <div className="d-flex align-items-center mb-3">
          <FontAwesomeIcon icon={faUser} size="3x" className="me-3" />
          <Card.Title className="mb-0">{employee.name}</Card.Title>
        </div>
        <Card.Text>
          <FontAwesomeIcon icon={faEnvelope} className="me-2" />
          <strong>Email:</strong> {employee.email}<br />
          <FontAwesomeIcon icon={faPhone} className="me-2" />
          <strong>Phone:</strong> {employee.phone}<br />
          <FontAwesomeIcon icon={faBuilding} className="me-2" />
          <strong>Department:</strong> <Badge bg="info">{employee.department}</Badge>
        </Card.Text>
        <div className="d-flex justify-content-between">
          <Button variant="outline-secondary" onClick={() => onUpdate(employee.id)}>Update</Button>
          <Button variant="outline-danger" onClick={() => onDelete(employee.id)}>Delete</Button>
        </div>
      </Card.Body>
    </Card>
  );
};

export default EmployeeCard;
