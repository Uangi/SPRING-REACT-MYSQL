import { Container } from 'react-bootstrap'; 
import { Route } from 'react-router-dom'; 
import './App.css'; 
import Header from './components/Header'; 
import List from './pages/List'; 
import Write from './pages/Write'; 
 
function App() { 
  return ( 
    <div> 
      <Header /> 
      <Container> 
        <Route path="/" exact={true} component={List} /> 
        <Route path="/write" exact={true} component={Write} /> 
      </Container> 
    </div> 
  ); 
} 
 
export default App;