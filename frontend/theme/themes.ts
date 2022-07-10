export type AppTheme = typeof light; 

const light = {
    name: 'light', 
    background: '#ffffff',
    components: {
        background: '#000000',
    },
    fonts: {
        logo: '#000000',
    }
}

const dark: AppTheme = {
    name: 'dark',
    background: '#000000',
    components: {
        background: '#ffffff',
    },
    fonts: {
        logo: '#ffffff',
    }
}


export const Themes: Record<string, AppTheme> = { dark, light };