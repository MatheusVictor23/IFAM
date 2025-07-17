import axios from "axios";
import type Missao from "../types/Missao";

const API_URL = "http://localhost:8080/api/missoes";


export const cadastrarMissao = async (missao: Omit<Missao, "id">) => {
  const response = await axios.post(API_URL, missao);
  return response.data;
};


export const listarMissoes = async (): Promise<Missao[]> => {
  const response = await axios.get(API_URL);
  return response.data;
};


export const deletarMissao = async (id: number) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data
}