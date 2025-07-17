import Layout from "@/components/layout"
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from "@/components/ui/card";
import React, { useEffect, useState } from "react";
import type Missao from "@/types/Missao";
import { deletarMissao, listarMissoes } from "@/services/missaoService";
import { Button } from "@/components/ui/button";
import { Send, Trash2 } from "lucide-react";


const Missoes = () => {

    const [missoes, setMissoes] = useState<Missao[]>([]);

    useEffect(() => {
        const fetchMissoes = async () => {
            const data = await listarMissoes();
            setMissoes(data);
        };
        fetchMissoes();
    }, []);

    return (
        <Layout>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {missoes.map((m) => (
                <Card key={m.id} className="h-[300px]">
                  <CardHeader>
                    <CardTitle className="text-lg">{m.nome}</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="mb-2">
                      <strong>Descrição:</strong> {m.descricao}
                    </div>
                    <div>
                      <strong>País:</strong> {m.pais}
                    </div>
                  </CardContent>
                  <CardFooter className="flex gap-2 justify-end">
                    <Button variant="destructive" size="icon" onClick={async () => await deletarMissao(m.id)}>
                      <Trash2 size={18} />
                    </Button>
                  </CardFooter>
                </Card>
              ))}
            </div>
        </Layout>
    )
}

export default Missoes;