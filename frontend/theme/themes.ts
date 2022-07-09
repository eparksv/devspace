export type AppTheme = typeof light; 

const light = {
    name: 'light', 
    background: '#ffffff',
}

const dark: AppTheme = {
    name: 'dark',
    background: '#000000',
}


export const Themes: Record<string, AppTheme> = { dark, light } 