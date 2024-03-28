package br.com.sistemadereparo.streetofragemvp.view

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.sistemadereparo.streetofragemvp.R
import br.com.sistemadereparo.streetofragemvp.databinding.ActivityMainBinding
import br.com.sistemadereparo.streetofragemvp.model.MainContract
import br.com.sistemadereparo.streetofragemvp.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private var velocidadeDeslocamentoDoPersonagem = 10
    private var botaoPressionado = false

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = MainPresenter(this)





        binding.btnLeft.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    presenter.botaoParaEsquerdaPressionado()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    presenter.botaoDeSocoLiberado()
                    true
                }
                else -> false
            }
        }

        binding.btnRight.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    presenter.botaoParaDiretaPressionado()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    presenter.botaoDeSocoLiberado()
                    true
                }
                else -> false
            }
        }
        binding.btnSoco.setOnClickListener {
            // Inicie a animação de soco
            presenter.botaoDeSocoClicado()

        }

    }



    override fun movePersonaParaEsquerda() {
        val posicaoX = binding.imgAdam.x
        val novaPosicaoX = posicaoX - velocidadeDeslocamentoDoPersonagem
        binding.imgAdam.x = novaPosicaoX
    }

    override fun movePersonaParaDireita() {
        val posicaoX = binding.imgAdam.x
        val novaPosicaoX = posicaoX + velocidadeDeslocamentoDoPersonagem
        binding.imgAdam.x = novaPosicaoX
    }

    override fun onStart() {
        super.onStart()
        binding.imgAdam.setImageResource(R.drawable.anim_em_guarda)
        (binding.imgAdam.drawable as? AnimationDrawable)?.start()
    }
    override fun pararPersona() {
        binding.imgAdam.setImageResource(R.drawable.anim_em_guarda)
        (binding.imgAdam.drawable as? AnimationDrawable)?.start()
    }

    override fun animacaoComecandoAndar() {
        binding.imgAdam.setImageResource(R.drawable.anim_andando)
        val animAndando = binding.imgAdam.drawable as? AnimationDrawable
        animAndando?.start()
    }

    override fun parandoAnimcaoDeAndar() {
        (binding.imgAdam.drawable as? AnimationDrawable)?.stop()
        binding.imgAdam.setImageResource(R.drawable.adam_zero)
    }

    override fun iniciandoAnimacaoDeSoco() {
        binding.imgAdam.setImageResource(R.drawable.anim_soco)
        val punch =binding.imgAdam.drawable as? AnimationDrawable
        punch?.isOneShot = true
        punch?.start()
    }

    override fun parandoAnimacaoDeSoco() {
        (binding.imgAdam.drawable as? AnimationDrawable)?.stop()
        binding.imgAdam.setImageResource(R.drawable.adam_zero)
    }

    override fun atualizarImagemDePersona(imageResId: Int) {
        binding.imgAdam.setImageResource(imageResId)
    }

    override fun movimentarPersonagem(deltaX: Int) {
        //config
        val layoutParams = binding.imgAdam.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.leftMargin += deltaX
        binding.imgAdam.layoutParams = layoutParams
    }


}