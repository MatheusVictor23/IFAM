import React, { useState } from "react";
import Layout from "../components/layout";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";


import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"


import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { cadastrarVoluntario } from "@/services/voluntarioService";

const Home = () => {

    const [modalVoluntario, setModalVoluntario] = useState(false);
    const [modalMissao, setModalMissao] = useState(false);
      const [form, setForm] = useState({
        passaporte: "",
        cpf: "",
        nome: "",
        sobrenome: "",
        data_nascimento: "",
        telefone: "",
        email: "",
        tipo_sanguineo: "",
        profissao: "",
        anos_experiencia: 0,
        situacao_saude: "",
        estado: "",
      });


    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };



    return (
        <Layout>
            <div className="flex flex-col md:flex-col gap-8 justify-end mb-8">
                <div className="w-full flex flex-row justify-end gap-5">
                    <button
                        className="px-4 py-2 bg-indigo-600 text-white rounded"
                        onClick={() => setModalVoluntario(true)}
                    >
                        Cadastrar Voluntário
                    </button>
                    <button
                        className="px-4 py-2 bg-indigo-600 text-white rounded"
                        onClick={() => setModalMissao(true)}
                    >
                        Cadastrar Missão
                    </button>
                </div>
                <div className="w-full grid grid-cols-3 gap-3">
                    <Card className="h-[300px]">
                      <CardHeader>
                        <CardTitle>Voluntários Disponíveis</CardTitle>
                        <CardDescription>Voluntários disponíveis para missões</CardDescription>
                      </CardHeader>
                      <CardContent className="w-full h-full flex justify-center items-center">
                        <p className="text-4xl">2</p>
                      </CardContent>
                    </Card>

                    <Card>
                      <CardHeader>
                        <CardTitle>Voluntários em Missão</CardTitle>
                        <CardDescription>Voluntários atualmente em missão</CardDescription>
                      </CardHeader>
                      <CardContent className="w-full h-full flex justify-center items-center">
                        <p className="text-4xl">7</p>
                      </CardContent>
                    </Card>


                    <Card>
                      <CardHeader>
                        <CardTitle>Missões</CardTitle>
                        <CardDescription>Missões em andamento</CardDescription>
                      </CardHeader>
                      <CardContent className="w-full h-full flex justify-center items-center">
                        <p className="text-4xl">3</p>
                      </CardContent>
                    </Card>
                </div>


            </div>

            {
                modalVoluntario && (
                    <div className="fixed inset-0 flex items-center justify-center bg-black/30 z-50">
                        <Card className="w-[800px]">
                            <CardHeader>
                              <CardTitle>Cadastrar Voluntário</CardTitle>
                              <CardDescription>
                                Preencha os dados abaixo para cadastrar um novo voluntário
                              </CardDescription>

                            </CardHeader>
                            <CardContent>
                                <form>
                                  <div className="flex flex-col gap-4">
                                    <div className="w-full grid grid-cols-2 gap-2">
                                        <div className="grid gap-2">
                                          <Label htmlFor="nome">Nome</Label>
                                          <Input id="nome" type="text" placeholder="Nome" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="sobrenome">Sobrenome</Label>
                                          <Input id="sobrenome" type="text" placeholder="Sobrenome" required onChange={handleChange}/>
                                        </div>
                                    </div>
                                    <div className="w-full grid grid-cols-3 gap-2">
                                        <div className="grid gap-2">
                                          <Label htmlFor="passaporte">Passaporte</Label>
                                          <Input id="passaporte" type="text" placeholder="Passaporte" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="cpf">CPF</Label>
                                          <Input id="cpf" type="text" placeholder="CPF" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="data_nascimento">Data de Nascimento</Label>
                                          <Input id="data_nascimento" type="date" required onChange={handleChange}/>
                                        </div>
                                    </div>
                                    <div className="w-full grid grid-cols-2 gap-2">
                                        <div className="grid gap-2">
                                          <Label htmlFor="telefone">Telefone</Label>
                                          <Input id="telefone" type="tel" placeholder="Telefone" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="email">Email</Label>
                                          <Input id="email" type="email" placeholder="m@example.com" required onChange={handleChange}/>
                                        </div>
                                    </div>
                                    <div className="w-full grid grid-cols-2 gap-2">
                                        <div className="grid gap-2">
                                          <Label htmlFor="tipo_sanguineo">Tipo Sanguíneo</Label>
                                          <Input id="tipo_sanguineo" type="text" placeholder="Tipo Sanguíneo" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="situacao_saude">Situação de Saúde</Label>
                                          <Input id="situacao_saude" type="text" placeholder="Situação de Saúde" required onChange={handleChange}/>
                                        </div>
                                    </div>
                                    <div className="w-full grid grid-cols-3 gap-2">
                                        <div className="grid gap-2">
                                          <Label htmlFor="profissao">Profissão</Label>
                                          <Input id="profissao" type="text" placeholder="Profissão" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="anos_experiencia">Anos de Experiência</Label>
                                          <Input id="anos_experiencia" type="number" min={3} placeholder="Anos de Experiência" required onChange={handleChange}/>
                                        </div>
                                        <div className="grid gap-2">
                                          <Label htmlFor="estado">Status</Label>
                                          <Input id="estado" type="text" placeholder="Estado" required onChange={handleChange}/>
                                        </div> 
                                    </div>

                                  </div>
                                </form>
                            </CardContent>
                            <CardFooter className="flex-col gap-2">
                              <Button type="submit" className="w-full" onClick={async () => await cadastrarVoluntario(form)}>
                                Cadastrar
                              </Button>
                              <Button variant="outline" className="w-full" onClick={() => setModalVoluntario(false)}>
                                Cancelar
                              </Button>
                            </CardFooter>
                        </Card>
                    </div>
            
                )
            }

            {
                modalMissao && (
                    <div className="fixed inset-0 flex items-center justify-center bg-black/30 z-50">
                        <Card className="w-[500px]">
                            <CardHeader>
                              <CardTitle>Cadastrar Missão</CardTitle>
                              <CardDescription>
                                Preencha os dados abaixo para cadastrar uma nova Missão
                              </CardDescription>

                            </CardHeader>
                            <CardContent>
                                <form >
                                  <div className="flex flex-col gap-4">
                                    <div className="w-full grid grid-row gap-1">
                                        <Label htmlFor="nome">Nome da missão</Label>
                                        <Input id="nome" type="text" placeholder="Nome" required/>
                                    </div>
                                    <div className="w-full grid grid-row gap-1">
                                        <Label htmlFor="descricao">Descrição</Label>
                                        <Input id="descricao" type="text" placeholder="Descrição" required/>
                                    </div>
                                    <div className="w-full grid grid-row gap-1">
                                        <Label htmlFor="pais">Pais de atuação</Label>
                                        <Select>
                                          <SelectTrigger className="w-[180px]">
                                            <SelectValue placeholder="País" />
                                          </SelectTrigger>
                                          <SelectContent>
                                            <SelectItem value="brasil">Brasil</SelectItem>
                                            <SelectItem value="haiti">Haiti</SelectItem>
                                            <SelectItem value="indonesia">Indonésia</SelectItem>
                                          </SelectContent>
                                        </Select>
                                    </div>
                                    </div>
                                </form>
                            </CardContent>
                            <CardFooter className="flex-col gap-2">
                              <Button type="submit" className="w-full" onClick={async () => await cadastrarVoluntario(form)}>
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

export default Home;