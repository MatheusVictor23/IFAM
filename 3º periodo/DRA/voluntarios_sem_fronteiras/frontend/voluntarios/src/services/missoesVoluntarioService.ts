import axios from "axios";
import type  Missao  from "../types/Missao";

const API_URL = "http://localhost:8080/api/missoes-voluntario";

export const listarMissoesPorVoluntario = async (voluntarioId: number): Promise<Missao[]> => {
  const response = await axios.get(`${API_URL}/${voluntarioId}`);
  return response.data;
};

export const deletarMissaoDoVoluntario = async (voluntarioId: number, missaoId: number) => {
  const response = await axios.delete(`${API_URL}/${voluntarioId}/missao/${missaoId}`);
  return response.data;
}