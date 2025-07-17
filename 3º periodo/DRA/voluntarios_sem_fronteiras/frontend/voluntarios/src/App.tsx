import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Missoes from './pages/Missoes';
import Voluntarios from './pages/Voluntarios';
import MissoesVoluntario from './pages/MissoesVoluntario';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/missoes" element={<Missoes />} />
                <Route path="/voluntarios" element={<Voluntarios />} />
                <Route path="/missoesVoluntario" element={<MissoesVoluntario />} />
            </Routes>
        </BrowserRouter>
    );
}


export default App;

