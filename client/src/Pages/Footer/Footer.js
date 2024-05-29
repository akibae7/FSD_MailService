import React from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer mt-auto py-3 bg-primary text-white">
            <Container>
                <Row>
                    <Col className="text-center">
                        <p>&copy; 2024 Employee Management System. All rights reserved.</p>
                    </Col>
                </Row>
            </Container>
        </footer>
    );
};

export default Footer;
