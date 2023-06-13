import { Route, Routes, BrowserRouter} from 'react-router-dom';
import './App.css';
import Home from './Home';
import Order from './Order';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/order/:id/:cost' element={<Order/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
