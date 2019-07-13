package com.freshdigitable.conclusiveanimator

import android.animation.Animator
import android.animation.ValueAnimator

fun ValueAnimator.setupWithConclusiveListener(
    listener: Animator.AnimatorListener? = null,
    onUpdate: (ValueAnimator) -> Unit
) {
    val l = ValueAnimatorConclusiveListener(listener, onUpdate)
    addListener(l)
    addUpdateListener(l.onUpdateListener)
}

private class ValueAnimatorConclusiveListener(
    private val listener: Animator.AnimatorListener? = null,
    onUpdate: (ValueAnimator) -> Unit
) : Animator.AnimatorListener {

    val onUpdateListener = ValueAnimator.AnimatorUpdateListener { onUpdate(it) }

    override fun onAnimationStart(animation: Animator?) {
        listener?.onAnimationStart(animation)
    }

    override fun onAnimationRepeat(animation: Animator?) {
        listener?.onAnimationRepeat(animation)
    }

    override fun onAnimationCancel(animation: Animator?) {
        (animation as? ValueAnimator)?.currentPlayTime = 0
        listener?.onAnimationCancel(animation)
    }

    override fun onAnimationEnd(animation: Animator?) {
        listener?.onAnimationEnd(animation)
        onAnimationTerminate(animation)
    }

    private fun onAnimationTerminate(animation: Animator?) {
        animation?.removeListener(this)
        (animation as? ValueAnimator)?.removeUpdateListener(onUpdateListener)
    }
}
