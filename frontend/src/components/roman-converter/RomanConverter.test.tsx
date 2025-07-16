import React from "react";
import { render, screen, fireEvent} from "@testing-library/react";
import RomanConverter from "./RomanConverter";

jest.mock("../../api/roman", () => ({
    fetchRomanNumeral: jest.fn(),
}));

import { fetchRomanNumeral } from "../../api/roman";

describe("RomanConverter", () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    it("shows an error when input is empty", async () => {
        render(<RomanConverter />);

        const button = screen.getByRole("button", { name: /convert to roman numeral/i });
        fireEvent.click(button);

        expect(await screen.findByText("Please enter a valid number.")).toBeInTheDocument();
    });

    it("shows an error when input is not a number", async () => {
        render(<RomanConverter />);

        const input = screen.getByLabelText(/enter a number/i);
        fireEvent.change(input, { target: { value: "abc" } });

        const button = screen.getByRole("button", { name: /convert to roman numeral/i });
        fireEvent.click(button);

        expect(await screen.findByText("Please enter a valid number.")).toBeInTheDocument();
    });

    it("shows the roman numeral result on success", async () => {
        // Mock successful response
        (fetchRomanNumeral as jest.Mock).mockResolvedValueOnce("X");

        render(<RomanConverter />);

        const input = screen.getByLabelText(/enter a number/i);
        fireEvent.change(input, { target: { value: "10" } });

        const button = screen.getByRole("button", { name: /convert to roman numeral/i });
        fireEvent.click(button);

        expect(await screen.findByText(/Roman Numeral:/i)).toHaveTextContent("Roman Numeral: X");
    });

    it("shows error message when fetch fails", async () => {
        (fetchRomanNumeral as jest.Mock).mockRejectedValueOnce(new Error("Network error"));

        render(<RomanConverter />);

        const input = screen.getByLabelText(/enter a number/i);
        fireEvent.change(input, { target: { value: "10" } });

        const button = screen.getByRole("button", { name: /convert to roman numeral/i });
        fireEvent.click(button);

        expect(await screen.findByText("Failed to connect to server.")).toBeInTheDocument();
    });

    it("shows error message when fetchRomanNumeral throws an error", async () => {
        (fetchRomanNumeral as jest.Mock).mockRejectedValueOnce(new Error("Invalid input from server"));

        render(<RomanConverter />);

        const input = screen.getByLabelText(/enter a number/i);
        fireEvent.change(input, { target: { value: "5000" } });

        const button = screen.getByRole("button", { name: /convert to roman numeral/i });
        fireEvent.click(button);

        expect(await screen.findByText("Failed to connect to server.")).toBeInTheDocument();
    });
});
