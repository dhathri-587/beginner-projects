import random
import time
import matplotlib.pyplot as plt

# --- STEP 1: WELCOME AND SETUP ---
print("🎮 Welcome to the Number Guessing Game!")
print("I am thinking of a number between 1 and 20.")

target_number = random.randint(1, 20)
attempts = 0
guess_history = []
time_history = []

start_time = time.time()

# --- STEP 2: THE GAME LOOP ---
while True:
    try:
        user_guess = int(input("Enter your guess (1-20): "))
        attempts += 1
        guess_history.append(user_guess)
        
        # Track elapsed time per guess
        elapsed = round(time.time() - start_time, 2)
        time_history.append(elapsed)
        
        # Check the guess
        if user_guess < target_number:
            print("📈 Too low! Try a higher number.")
        elif user_guess > target_number:
            print("📉 Too high! Try a lower number.")
        else:
            print(f"🎉 Correct! You found it in {attempts} attempts!")
            break
            
    except ValueError:
        print("❌ Invalid input! Please enter a whole number.")

# --- STEP 3: ANALYSE PERFORMANCE ---
print("\n📊 Generating your Game Performance Chart...")

plt.figure(figsize=(8, 4))
plt.plot(range(1, attempts + 1), guess_history, marker='o', color='b', label='Your Guesses')
plt.axhline(y=target_number, color='r', linestyle='--', label=f'Target ({target_number})')

plt.title('Your Guessing Pattern')
plt.xlabel('Attempt Number')
plt.ylabel('Value Guessed')
plt.xticks(range(1, attempts + 1))
plt.grid(True, linestyle=':', alpha=0.6)
plt.legend()
plt.show()
