// frontend/src/App.tsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Dashboard from './pages/Dashboard';
import NotFound from './pages/NotFound';

export default function App() {
  return (
    <BrowserRouter>
    <Routes>
      {/* 기본 레이아웃 */}
      <Route path="/" element={<Layout />}>
            {/* / -> Dashboard */}
          <Route index element={<Dashboard />} />
          {/* 존재하지 않는 라우트 -> 404 */}
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}