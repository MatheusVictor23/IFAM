import React, { useEffect, useState } from "react";
import Layout from "@/components/layout";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { useParams } from "react-router-dom";
import { listarMissoesPorVoluntario } from "@/services/missoesVoluntarioService";
import type MissaoVoluntario from "@/types/MissaoVoluntario";
// Tipos de exemplo


const MissoesVoluntario = () => {
  const { id } = useParams();
  const [missoes, setMissoes] = useState<MissaoVoluntario[]>([]);

  useEffect(() => {
    if (id) {
      listarMissoesPorVoluntario(Number(id)).then((data) => {
        const missoesVoluntario: MissaoVoluntario[] = data.map((m: any) => ({
          voluntario: m.voluntario,
          missao: m.missao,
          cidadeAtuacao: m.cidadeAtuacao,
          dataInicio: m.dataInicio,
          dataFim: m.dataFim,
          avaliacao: m.avaliacao,
          parecer: m.parecer,
        }));
        setMissoes(missoesVoluntario);
      });
    }
  }, [id]);

  return (
    <Layout>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {missoes.map((m, index) => (
          <Card key={index}>
            <CardHeader>
              <CardTitle>{m.voluntario}</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="mb-2">
                <strong>Missão:</strong> {m.missao}
              </div>
              <div className="mb-2">
                <strong>Data de Início:</strong> {m.dataInicio}
              </div>
              <div className="mb-2">
                <strong>Data de Fim:</strong> {m.dataFim}
              </div>
              <div className="mb-2">
                <strong>Avaliação:</strong> {m.avaliacao}
              </div>
              <div>
                <strong>Parecer:</strong> {m.parecer}
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </Layout>
  );
};

export default MissoesVoluntario;