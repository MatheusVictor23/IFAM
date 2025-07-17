import React, { useState, type ReactNode } from "react";
import { AppWindowIcon, CodeIcon } from "lucide-react"

import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
  Tabs,
  TabsContent,
  TabsList,
  TabsTrigger,
} from "@/components/ui/tabs"
import { Link } from "react-router-dom";

type LayoutProps = {
    children: ReactNode;
};

const Layout = ({ children }: LayoutProps) => {

    return (
        <div className="min-h-screen flex flex-col bg-gray-50">
            
            <header className="bg-indigo-600 py-4 shadow flex flex-row items-center justify-between px-8">
                <h1 className="text-3xl text-white font-bold text-center">
                    Voluntários sem Fronteiras
                </h1>
                
                <div className="flex w-full max-w-sm flex-col gap-6">
                <Tabs defaultValue="Dashboard" className="w-[400px]">
                  <TabsList>
                    <TabsTrigger value="Dashboard">
                        <Link to="/">Dashboard</Link>
                    </TabsTrigger>
                    <TabsTrigger value="voluntarios">
                        <Link to="/voluntarios">Voluntários</Link>
                    </TabsTrigger>
                    <TabsTrigger value="missoes">
                        <Link to="/missoes">Missões</Link>
                    </TabsTrigger>
                  </TabsList>
                </Tabs>
                </div>
            </header>


            <main className="flex-1 container mx-auto px-4 py-8">
                {children}
            </main>


            <footer className="bg-indigo-600 py-2 text-center text-white">
                © {new Date().getFullYear()} Voluntários sem Fronteiras
            </footer>
        </div>
    );
};

export default Layout;