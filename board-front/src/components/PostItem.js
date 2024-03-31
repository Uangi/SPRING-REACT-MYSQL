import React from 'react'; 
import { Card } from 'react-bootstrap'; 
import { Link } from 'react-router-dom'; 
 
const PostItem = () => { 
  return ( 
    <Card> 
      <Card.Body> 
        <Card.Title>제목</Card.Title> 
        <Card.Text>내용</Card.Text> 
        <Link to="" className="btn btn-primary"> 
          자세히보기 
        </Link> 
      </Card.Body> 
    </Card> 
  ); 
}; 
 
export default PostItem;