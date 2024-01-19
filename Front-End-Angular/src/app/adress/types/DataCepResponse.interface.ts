export interface DataCepResponse{
    cep: string,
    logradouro: string,
    complemento: string,
    bairro: string,
    localidade: string,
    uf: string,
    erro?: boolean
}