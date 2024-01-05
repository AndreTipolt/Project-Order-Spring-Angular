export interface TokenSucessLogin{
    acess_token: string,
    token_type: string,
    refresh_token: string,
    expires_in: number,
    scope: string,
    userFirstName: string,
    userId: number
    
    // If error
    // error?: string,
    // error_description?: string
}