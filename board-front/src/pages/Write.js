
import React from 'react'; 
import { Button, Form } from 'react-bootstrap'; 
 
const Write = () => { 
  return ( 
    <Form> 
      <Form.Group className="mb-3"> 
        <Form.Label>제목</Form.Label> 
        <Form.Control /> 
      </Form.Group> 
      <Form.Group className="mb-3"> 
        <Form.Label>내용</Form.Label> 
        <Form.Control as="textarea" rows={3} /> 
      </Form.Group> 
      <Button>글쓰기</Button> 
    </Form> 
  ); 
}; 
 
export default Write;