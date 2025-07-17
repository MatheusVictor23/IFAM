import axios from "axios";
import { type VoluntarioInput } from "../types/VoluntarioInput";

const API_URL = "http://localhost:8080/api/voluntarios";

export const cadastrarVoluntario = async (voluntario: VoluntarioInput) => {
  const response = await axios.post(API_URL, voluntario);
  return response.data;
};

export const listarVoluntarios = async (): Promise<VoluntarioInput[]> => {
  const response = await axios.get(API_URL);
  return response.data;
};



export const deletarVoluntario = async (id: number) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};