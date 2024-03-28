package br.com.sistemadereparo.streetofragemvp.model

interface MainContract {

    interface View {

        fun movePersonaParaEsquerda()
        fun movePersonaParaDireita()
        fun pararPersona()
        fun animacaoComecandoAndar()
        fun parandoAnimcaoDeAndar()
        fun iniciandoAnimacaoDeSoco()
        fun parandoAnimacaoDeSoco()
        fun atualizarImagemDePersona(imageResId: Int)
        fun movimentarPersonagem(deltaX: Int)
    }

    interface Presenter {
        fun botaoParaEsquerdaPressionado()
        fun botaoParaDiretaPressionado()
        fun botaoParaEsquerdaLiberado()
        fun botaoParaDireitaLiberado()
        fun botaoDeSocoClicado()
        fun botaoDeSocoLiberado()
    }
}