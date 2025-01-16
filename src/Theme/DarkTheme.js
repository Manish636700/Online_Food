const { createTheme } = require("@mui/material");

export const darkTheme=createTheme({
    palette:{
        mode:"dark",
        primary:{
            main :"#B42B4D"
        },

        secondary:{
            main:"#B44D6B"
        },

        black:{
            main:"#242B2E"
        },

        background:{
            main:"#0000000",
            default:"#0D0D0D",
            paper:"#0D0D0D",
        },
        textColor:{
            main:"#111111"
        }

    }

})