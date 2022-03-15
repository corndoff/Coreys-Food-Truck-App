package com.example.learningandroid.fragments

import android.app.Activity
import android.app.AppComponentFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import com.example.learningandroid.HamburgerItem
import com.example.learningandroid.MainActivity
import com.example.learningandroid.R


class HamburgersFragment : Fragment(R.layout.fragment_hamburgers) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rgMeat = view.findViewById<RadioGroup>(R.id.rgMeat)
        val btnOrder = view.findViewById<Button>(R.id.btnOrder)
        val cbCheese = view.findViewById<CheckBox>(R.id.cbCheese)
        val cbOnion = view.findViewById<CheckBox>(R.id.cbOnion)
        val cbLettuce = view.findViewById<CheckBox>(R.id.cbLettuce)
        val rgCheese = view.findViewById<RadioGroup>(R.id.rgCheese)
        val btnBack = view.findViewById<Button>(R.id.btnBack)
        val orderFragment = OrderFragment()
        val cartFragment = CartFragment()


        cbCheese.setOnClickListener{
            if(cbCheese.isChecked){
                rgCheese.isVisible = true
            }else{
                rgCheese.isVisible = false
            }
        }


        btnOrder.setOnClickListener(View.OnClickListener {
            val meat = view.findViewById<RadioButton>(rgMeat.checkedRadioButtonId)
            val cheeseType = view.findViewById<RadioButton>(rgCheese.checkedRadioButtonId)
            val cheese = cbCheese.isChecked
            val onion = cbOnion.isChecked
            val lettuce = cbLettuce.isChecked
            val hamburgerItem = HamburgerItem(meat.text.toString(),  (if(cheese) "\n${cheeseType.text} Cheese" else "No cheese"),
                (if(onion) "\nOnion" else "No Onion"),
                (if(lettuce) "\nLettuce" else "No Lettuce"))
            val orderString = "${meat.text} Hamburger\n" +
                    (if(cheese) "\n${cheeseType.text} Cheese" else "No cheese") +
                    (if(onion) "\nOnion" else "No onion") +
                    (if(lettuce) "\nLettuce" else "No lettuce")
            (activity as MainActivity).addItemToList(orderString)
            val bundle = Bundle()
            //bundle.putString("hamburgerOrder", orderString)
            //bundle.putSerializable("hamburgerOrder", hamburgerItem)
            //cartFragment.arguments = bundle

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, cartFragment)
                commit()
            }
        })

        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, orderFragment)
                commit()
            }
        }
    }
}