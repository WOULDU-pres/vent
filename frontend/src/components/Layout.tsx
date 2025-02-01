// frontend/src/components/Layout.tsx
import { Outlet } from 'react-router-dom';

export default function Layout() {
  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      <header className="bg-white shadow-sm p-4">
        <h1 className="text-xl font-bold">
          Vent 이벤트 관리 시스템
        </h1>
      </header>

      <main className="flex-1 container mx-auto p-4">
        <Outlet />
      </main>

      <footer className="bg-white shadow-inner p-4 text-center">
        <p className="text-sm text-gray-500">© 2024 Vent</p>
      </footer>
    </div>
  );
}
