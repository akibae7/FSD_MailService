import React, { useEffect, useState } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Alert from 'react-bootstrap/Alert';
import { useNavigate } from 'react-router-dom';
import ConfirmModal from './ConfirmModal';
import EmployeeCard from './EmployeeCard';
import './EmployeeCard.css';

const Dashboard = () => {
  const [employees, setEmployees] = useState([]);
  const [showAlert, setShowAlert] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/employees');
        const data = await response.json();
        setEmployees(data);
      } catch (error) {
        console.error('Error fetching employees:', error.message);
      }
    };

    fetchEmployees();
  }, []);

  const handleDelete = async () => {
    if (!selectedEmployee) return;

    try {
      const response = await fetch(`http://localhost:8080/api/employee/${selectedEmployee}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setEmployees(employees.filter((employee) => employee.id !== selectedEmployee));
        console.log(`Employee with ID ${selectedEmployee} deleted successfully`);
        setShowAlert(true);
        setTimeout(() => {
          setShowAlert(false);
        }, 3000);
      } else {
        console.error(`Failed to delete employee with ID ${selectedEmployee}`);
      }
    } catch (error) {
      console.error(`Error deleting employee with ID ${selectedEmployee}:`, error.message);
    } finally {
      setShowModal(false);
      setSelectedEmployee(null);
    }
  };

  const handleUpdate = (employeeID) => {
    navigate(`/employee/${employeeID}`);
  };

  const openModal = (employeeID) => {
    setSelectedEmployee(employeeID);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedEmployee(null);
  };

  return (
    <>
      <Container className="mt-5">
        <Row>
          <Col>
            <h1 className="text-center">Employees</h1>
            {showAlert && (
              <Alert variant="success" onClose={() => setShowAlert(false)} dismissible>
                Employee deleted successfully!
              </Alert>
            )}
            <Row>
              {employees.map((employee) => (
                <Col key={employee.id} sm={12} md={6} lg={4}>
                  <EmployeeCard
                    employee={employee}
                    onUpdate={handleUpdate}
                    onDelete={openModal}
                  />
                </Col>
              ))}
            </Row>
          </Col>
        </Row>
      </Container>
      <ConfirmModal
        show={showModal}
        handleClose={closeModal}
        handleConfirm={handleDelete}
        message="Are you sure you want to delete this employee?"
      />
    </>
  );
};

export default Dashboard;
