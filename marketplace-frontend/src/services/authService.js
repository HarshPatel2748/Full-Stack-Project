const API_BASE = "http://localhost:8080/api";

export const userSignup = async (signupData) => {
  const response = await fetch(`${API_BASE}/user/auth/signup`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(signupData),
  });

  if (!response.ok) {
    const error = await response.text();
    throw new Error(error || "Signup failed");
  }

  return response.json();
};
