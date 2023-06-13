package com.huseyincn.kreditest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.materialswitch.MaterialSwitch
import com.huseyincn.kreditest.R
import com.huseyincn.kreditest.viewmodel.CreditViewModel
import java.lang.Exception

class ApplyCredit : Fragment() {

    private lateinit var viewModel: CreditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apply_credit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CreditViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner) {
            val selected: Fragment
            System.out.println("it changed ${it.result}")
            try {
                if (it.result.replace("[^\\d.-]".toRegex(), "").toFloat() > 0)
                    selected = positive()
                else
                    selected = negative()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.conta, selected).addToBackStack(null).commit()
            } catch (e: Exception) {
                System.out.println(e.message)
            }
        }

        val spinner: Spinner = view.findViewById(R.id.spinner1)
        adapterSpinner(spinner, R.array.chechking_status_array)

        val spinner2: Spinner = view.findViewById(R.id.spinner2)
        adapterSpinner(spinner2, R.array.credit_history_array)

        val spinner3: Spinner = view.findViewById(R.id.spinner3)
        adapterSpinner(spinner3, R.array.purpose_array)

        val spinner4: Spinner = view.findViewById(R.id.spinner4)
        adapterSpinner(spinner4, R.array.saving_status_array)

        val spinner5: Spinner = view.findViewById(R.id.spinner5)
        adapterSpinner(spinner5, R.array.employment_array)

        val spinner6: Spinner = view.findViewById(R.id.spinner6)
        adapterSpinner(spinner6, R.array.installment_commitment_array)

        val spinner7: Spinner = view.findViewById(R.id.spinner7)
        adapterSpinner(spinner7, R.array.other_parties_array)

        val spinner8: Spinner = view.findViewById(R.id.spinner8)
        adapterSpinner(spinner8, R.array.residence_since_array)

        val spinner9: Spinner = view.findViewById(R.id.spinner9)
        adapterSpinner(spinner9, R.array.property_magnitude_array)

        val spinner10: Spinner = view.findViewById(R.id.spinner10)
        adapterSpinner(spinner10, R.array.other_payment_plans_array)

        val spinner11: Spinner = view.findViewById(R.id.spinner11)
        adapterSpinner(spinner11, R.array.housing_array)

        val spinner12: Spinner = view.findViewById(R.id.spinner12)
        adapterSpinner(spinner12, R.array.existing_credits_array)

        val spinner13: Spinner = view.findViewById(R.id.spinner13)
        adapterSpinner(spinner13, R.array.job_array)

        val spinner14: Spinner = view.findViewById(R.id.spinner14)
        adapterSpinner(spinner14, R.array.sex_array)

        val spinner15: Spinner = view.findViewById(R.id.spinner15)
        adapterSpinner(spinner15, R.array.marriage_array)

        val numberPicker1: NumberPicker = view.findViewById(R.id.number1)
        numberPicker1.minValue = 18
        numberPicker1.maxValue = 100
        numberPicker1.value = 30
        val textView1: TextView = view.findViewById(R.id.textview16)
        textView1.setOnClickListener {
            val a = numberPicker1.value
            numberPicker1.value = a - 1
        }
        val textView2: TextView = view.findViewById(R.id.textview17)
        textView2.setOnClickListener {
            val a = numberPicker1.value
            numberPicker1.value = a + 1
        }

        val numberPicker2: NumberPicker = view.findViewById(R.id.number2)
        numberPicker2.minValue = 0
        numberPicker2.maxValue = 10
        numberPicker2.value = 0
        val textView3: TextView = view.findViewById(R.id.textview20)
        val textView4: TextView = view.findViewById(R.id.textview21)
        textView3.setOnClickListener {
            numberPicker2.value = numberPicker2.value - 1
        }
        textView4.setOnClickListener {
            numberPicker2.value = numberPicker2.value + 1
        }

        val editText: EditText = view.findViewById(R.id.edittex2)
        val editText2: EditText = view.findViewById(R.id.edittex1)
        val switch: MaterialSwitch = view.findViewById(R.id.switch1)
        val button: Button = view.findViewById(R.id.button1)
        button.setOnClickListener {
            sendRequest(
                spinner.selectedItem.toString(),
                editText.text.toString(),
                spinner2.selectedItem.toString(),
                spinner3.selectedItem.toString(),
                editText2.text.toString(),
                spinner4.selectedItem.toString(),
                spinner5.selectedItem.toString(),
                (spinner6.selectedItemPosition + 1).toString(),
                spinner7.selectedItem.toString(),
                (spinner8.selectedItemPosition + 1).toString(),
                spinner9.selectedItem.toString(),
                numberPicker1.value.toString(),
                spinner10.selectedItem.toString(),
                spinner11.selectedItem.toString(),
                (spinner12.selectedItemPosition + 1).toString(),
                spinner13.selectedItem.toString(),
                numberPicker2.value.toString(),
                "yes",
                if (switch.isChecked) "yes" else "no",
                spinner14.selectedItem.toString(),
                spinner15.selectedItem.toString()
            )
        }
    }

    fun adapterSpinner(spinner: Spinner, array: Int) {
        ArrayAdapter.createFromResource(
            requireContext(), array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    fun sendRequest(
        checking_status: String,
        duration: String,
        credit_history: String,
        purpose: String,
        credit_amount: String,
        savings_status: String,
        employment: String,
        installment_commitment: String,
        other_parties: String,
        residence_since: String,
        property_magnitude: String,
        age: String,
        other_payment_plans: String,
        housing: String,
        existing_credits: String,
        job: String,
        num_dependents: String,
        own_telephone: String,
        foreign_worker: String,
        sex: String,
        marriage: String
    ) {
        viewModel.getData(
            checking_status,
            duration,
            credit_history,
            purpose,
            credit_amount,
            savings_status,
            employment,
            installment_commitment,
            other_parties,
            residence_since,
            property_magnitude,
            age,
            other_payment_plans,
            housing,
            existing_credits,
            job,
            num_dependents,
            own_telephone,
            foreign_worker,
            sex,
            marriage
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}