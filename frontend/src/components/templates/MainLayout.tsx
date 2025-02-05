// src/components/templates/MainLayout/MainLayout.tsx
import { NavLink } from "react-router-dom";

export const MainLayout = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className="bg-surface-100 flex min-h-screen flex-col">
      <header className="bg-surface-0 shadow-sm">
        <nav className="container flex h-16 items-center justify-between">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `text-xl font-bold transition-colors ${
                isActive
                  ? "text-primary"
                  : "text-surface-800 hover:text-primary/90"
              }`
            }
          >
            VENT
          </NavLink>
          <div className="flex gap-6">
            <CustomNavLink to="/flash-deals">100원 딜</CustomNavLink>
            <CustomNavLink to="/dashboard">대시보드</CustomNavLink>
          </div>
        </nav>
      </header>

      <main className="container flex-1 px-4 py-8 sm:px-6 lg:px-8">
        {children}
      </main>

      <footer className="bg-surface-0 text-surface-600 border-t py-4 text-center">
        © 2025 VENT. All rights reserved.
      </footer>
    </div>
  );
};

const CustomNavLink = ({ to, children }: { to: string; children: string }) => (
  <NavLink
    to={to}
    className={({ isActive }) =>
      `rounded-md px-3 py-2 text-sm font-medium transition-colors ${
        isActive
          ? "bg-primary/10 text-primary"
          : "text-surface-600 hover:bg-surface-200 hover:text-surface-900"
      }`
    }
  >
    {children}
  </NavLink>
);
