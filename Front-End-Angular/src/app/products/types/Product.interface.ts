export interface Product{
    
    id?: number;
    name: string;
    fowardPrice: number;
    spotPrice: number;
    installments: number;
    description: string;
    pixDiscount: number;
    category?: number;
    valueInstallment?: number;
    imageURL: string;
}