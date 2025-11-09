import tkinter as tk
from tkinter import messagebox

def calculate_payment():
    try:
        # Get values from input fields
        rate = float(entry_rate.get()) / 100 / 12  # annual → monthly rate
        n = int(entry_payments.get())  # number of payments
        principal = float(entry_principal.get())  # loan amount

        # Formula for monthly payment
        monthly = (principal * rate) / (1 - (1 + rate) ** -n)

        # Show result in entry box
        entry_monthly.delete(0, tk.END)
        entry_monthly.insert(0, f"{monthly:.2f}")

    except Exception:
        messagebox.showerror("Error", "Please enter valid numbers!")

# ---- GUI Window ----
root = tk.Tk()
root.title("Loan Calculator")
root.geometry("400x250")

# Labels and Entries
tk.Label(root, text="Annual Interest Rate (%)").grid(row=0, column=0, sticky="e", padx=10, pady=5)
entry_rate = tk.Entry(root)
entry_rate.grid(row=0, column=1, pady=5)

tk.Label(root, text="Number of Payments").grid(row=1, column=0, sticky="e", padx=10, pady=5)
entry_payments = tk.Entry(root)
entry_payments.grid(row=1, column=1, pady=5)

tk.Label(root, text="Loan Principal").grid(row=2, column=0, sticky="e", padx=10, pady=5)
entry_principal = tk.Entry(root)
entry_principal.grid(row=2, column=1, pady=5)

tk.Label(root, text="Monthly Payment").grid(row=3, column=0, sticky="e", padx=10, pady=5)
entry_monthly = tk.Entry(root)
entry_monthly.grid(row=3, column=1, pady=5)

# Buttons
tk.Button(root, text="Calculate", command=calculate_payment).grid(row=4, column=0, pady=15)
tk.Button(root, text="Quit", command=root.destroy).grid(row=4, column=1, pady=15)

root.mainloop()
