// src/components/templates/MainLayout/MainLayout.tsx
import { NavLink } from 'react-router-dom' 

export const MainLayout = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className="min-h-screen flex flex-col bg-surface-100">
      <header className="bg-surface-0 shadow-sm">
        <nav className="container flex items-center justify-between h-16">
          <NavLink 
            to="/" 
            className={({ isActive }) => 
              `text-xl font-bold transition-colors ${
                isActive ? 'text-primary' : 'text-surface-800 hover:text-primary/90'
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

      <main className="flex-1 container py-8 px-4 sm:px-6 lg:px-8">
        {children}
      </main>

      <footer className="bg-surface-0 border-t py-4 text-center text-surface-600">
        © 2025 VENT. All rights reserved.
      </footer>
    </div>
  )
}

const CustomNavLink = ({ to, children }: { to: string; children: string }) => (
  <NavLink
    to={to}
    className={({ isActive }) => 
      `px-3 py-2 rounded-md transition-colors text-sm font-medium ${
        isActive 
          ? 'bg-primary/10 text-primary' 
          : 'text-surface-600 hover:bg-surface-200 hover:text-surface-900'
      }`
    }
  >
    {children}
  </NavLink>
)
