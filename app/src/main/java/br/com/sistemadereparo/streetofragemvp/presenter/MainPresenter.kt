package br.com.sistemadereparo.streetofragemvp.presenter

import android.os.Handler
import br.com.sistemadereparo.streetofragemvp.model.MainContract

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter  {
    private var estaMovendoParaEsquerda = false
    private var estaMovendoParaDireita = false
    private val handler = Handler()



    override fun botaoParaEsquerdaPressionado() {
        view.animacaoComecandoAndar()
        estaMovendoParaEsquerda = true
        movendoParaEsquerda()
    }

    override fun botaoParaDiretaPressionado() {
        view.animacaoComecandoAndar()
        estaMovendoParaDireita = true
        movendoParaDireita()
    }

    override fun botaoParaEsquerdaLiberado() {

        estaMovendoParaEsquerda = false
        estaMovendoParaDireita = false
        view.pararPersona()
        view.parandoAnimcaoDeAndar()
    }

    private fun movendoParaEsquerda() {
        handler.postDelayed({
            if (estaMovendoParaEsquerda) {
                view.movePersonaParaEsquerda()
                movendoParaEsquerda()
            }
        }, 50)
    }

    private fun movendoParaDireita() {
        handler.postDelayed({
            if (estaMovendoParaDireita) {
                view?.movePersonaParaDireita()
                movendoParaDireita()
            }
        }, 50)
    }

    override fun botaoParaDireitaLiberado() {
        view?.parandoAnimcaoDeAndar()
    }

    override fun botaoDeSocoClicado() {
        view?.iniciandoAnimacaoDeSoco()
    }

    override fun botaoDeSocoLiberado() {
        estaMovendoParaEsquerda = false
        estaMovendoParaDireita = false
        view?.pararPersona()    }
}