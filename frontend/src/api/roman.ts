export const API_PREFIX = "/api";

export async function fetchRomanNumeral(value: string): Promise<string> {
    const response = await fetch(`${API_PREFIX}/romannumeral?query=${value}`);

    if (!response.ok) {
        const text = await response.text();
        throw new Error(text || "Unknown server error");
    }

    const data = await response.json();
    if (!data.output) {
        throw new Error("Invalid response from server");
    }

    console.log(data.output);
    return data.output;
}