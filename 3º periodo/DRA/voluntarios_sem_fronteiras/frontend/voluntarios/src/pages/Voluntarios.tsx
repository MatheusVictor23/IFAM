import React, { useEffect, useState } from "react";
import Layout from "@/components/layout";
import { Card, CardHeader, CardTitle, CardContent, CardFooter, CardDescription } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Trash2, Send } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea"
import { listarVoluntarios } from "@/services/voluntarioService";
import { deletarVoluntario } from "@/services/voluntarioService";


type Voluntario = {
  passaporte: string;
  cpf: string;
  nomeCompleto: string;
  dataNascimento: string;
  idade: number;
  telefone: string;
  email: string;
  tipoSanguineo: string;
  profissao: string;
  anosExperiencia: number;
  situacaoSaude: string;
  status: string;
};

const Voluntarios = () => {
  const navigate = useNavigate();

  useEffect(() => {
  listarVoluntarios().then(setVoluntarios);
  }, []);

const [voluntarios, setVoluntarios] = useState<Voluntario[]>([]);

  const [modalMissao, setModalMissao] = useState(false);


  return (
    <Layout>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {voluntarios.map((v) => (
          <Card key={v.id} className="flex flex-col justify-between h-full">
            <CardHeader onClick={() => navigate(`/missoesVoluntario/${v.id}`)}>
              <div className="flex items-center justify-between">
                <CardTitle>{v.nome}</CardTitle>
                <Badge variant={v.disponivel ? "secondary" : "primary"}>
                  {v.disponivel ? "Disponível" : "Em missão"}
                </Badge>
              </div>
            </CardHeader>
            <CardContent>
              <div className="text-sm text-gray-700 mb-2">
                <strong>Nome:</strong> {v.nome}
              </div>
              <div className="text-sm text-gray-700 mb-2">
                <strong>Email:</strong> {v.email}
              </div>
              <div className="text-sm text-gray-700 mb-2">
                <strong>Tipo Sanguíneo:</strong> {v.tipo_sanguineo}
              </div>
              <div className="text-sm text-gray-700 mb-2">
                <strong>Profissão:</strong> {v.profissao}
              </div>
              <div className="text-sm text-gray-700 mb-2">
                <strong>Anos de Experiência:</strong> {v.anos_experiencia}
              </div>
              <div className="text-sm text-gray-700">
                <strong>Situação de Saúde:</strong> {v.situacao_saude}
              </div>
            </CardContent>
            <CardFooter className="flex gap-2 justify-end">
              <Button variant="destructive" size="icon" onClick={async () => await deletarVoluntario(v.id)}>
                <Trash2 size={18} />
              </Button>
              <Button variant="default" size="icon" onClick={() => setModalMissao(true)}>
                <Send size={18} />
              </Button>
            </CardFooter>
          </Card>
        ))}
      </div>


        
            {
                modalMissao && (
                    <div className="fixed inset-0 flex items-center justify-center bg-black/30 z-50">
                        <Card className="w-[600px]">
                            <CardHeader>
                              <CardTitle>Avaliar Desempenho do voluntário</CardTitle>
                              <CardDescription>
                                Preencha os dados abaixo para avaliar o desempenho do voluntário
                              </CardDescription>

                            </CardHeader>
                            <CardContent>
                                <form>
                                  <div className="flex flex-col gap-4">
                                    <div className="w-full grid grid-row gap-1">
                                        <Label htmlFor="nome">Nome da missão</Label>
                                        <Input id="nome" type="text" placeholder="Nome" required/>
                                    </div>
                                    <div className="w-full grid grid-row gap-1">
                                        <Label htmlFor="descricao">Cidade Atuação</Label>
                                        <Input id="descricao" type="text" placeholder="cidade" required/>
                                    </div>
                                    <div className="grid gap-2">
                                      <Label htmlFor="data_inicio">Data de Início</Label>
                                      <Input id="data_inicio" type="date" required />
                                    </div>
                                    <div className="grid gap-2">
                                      <Label htmlFor="data_fim">Final da Missão</Label>
                                      <Input id="data_fim" type="date" required />
                                    </div>
                                    <div className="grid gap-2">
                                      <Label htmlFor="Parecer">Avaliação</Label>
                                      <Textarea id="Parecer" placeholder="Descreva o desempenho do voluntário" />
                                    </div>
                                  </div>
                                </form>
                            </CardContent>
                            <CardFooter className="flex-col gap-2">
                              <Button type="submit" className="w-full">
                                Cadastrar
                              </Button>
                              <Button variant="outline" className="w-full" onClick={() => setModalMissao(false)}>
                                Cancelar
                              </Button>
                            </CardFooter>
                        </Card>
                    </div>
                )
            }
    </Layout>

    

  );
};

export default Voluntarios;