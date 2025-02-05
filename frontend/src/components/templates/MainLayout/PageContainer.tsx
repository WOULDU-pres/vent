// src/components/templates/MainLayout/PageContainer.tsx
import { ReactNode } from "react";
import { Link } from "react-router-dom";

export const PageContainer = ({ children }: { children: ReactNode }) => (
  <div className="page-container">
    <header className="header">
      <nav>
        <Link to="/flash-deals">100원 딜</Link>
        <Link to="/create">딜 생성</Link>
      </nav>
    </header>
    <main>{children}</main>
  </div>
);
