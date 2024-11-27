package com.alessandroreis.recyclerviewkotlin

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.alessandroreis.recyclerviewkotlin.model.Email

class EmailAdapter (val emails: MutableList<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun getItemCount(): Int = emails.size

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtIcon = itemView.findViewById<TextView>(R.id.txt_icon)
        val txtUser = itemView.findViewById<TextView>(R.id.txt_user)
        val txtSubject = itemView.findViewById<TextView>(R.id.txt_subject)
        val txtPreview = itemView.findViewById<TextView>(R.id.txt_preview)
        val txtDate = itemView.findViewById<TextView>(R.id.txt_date)
        val imgStar = itemView.findViewById<ImageView>(R.id.img_star)

        fun bind(email: Email) {
            with(email) {
                val hash = user.hashCode()
                txtIcon.text = user.first().toString()
                txtIcon.background = itemView.oval(Color.rgb(hash, hash / 2, 0))
                txtUser.text = user
                txtSubject.text = subject
                txtPreview.text = preview
                txtDate.text = date

                txtUser.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                txtSubject.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                txtDate.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)

                imgStar.setImageResource(
                    if (stared) R.drawable.baseline_star_24
                    else R.drawable.baseline_star_border_24
                )
            }
        }
    }

}

fun View.oval(@ColorInt color: Int): ShapeDrawable {
    val oval = ShapeDrawable(OvalShape())
    with(oval) {
        intrinsicHeight = height
        intrinsicWidth = width
        paint.color = color
    }
    return oval
}