export interface Product{

    id: string;
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
