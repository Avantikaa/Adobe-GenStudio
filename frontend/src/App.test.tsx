import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

// Mock RomanConverter to simplify
jest.mock("./components/RomanConverter", () => () => <div>RomanConverter Mock</div>);

// Capture props passed to Provider
const mockProvider = jest.fn(({ children }) => <div>{children}</div>);

// Mock the Provider component from @adobe/react-spectrum
jest.mock("@adobe/react-spectrum", () => {
  const originalModule = jest.requireActual("@adobe/react-spectrum");
  return {
    ...originalModule,
    Provider: (props: any) => {
      mockProvider(props);
      return <div>{props.children}</div>;
    },
  };
});

describe("App", () => {
  beforeEach(() => {
    // Clear mock before each test
    mockProvider.mockClear();
  });

  it("uses light theme when prefers-color-scheme is light", () => {
    (window.matchMedia as any) = jest.fn().mockImplementation(query => ({
      matches: false, // Light mode
      media: query,
      onchange: null,
      addListener: jest.fn(),
      removeListener: jest.fn(),
      addEventListener: jest.fn(),
      removeEventListener: jest.fn(),
      dispatchEvent: jest.fn(),
    }));

    render(<App />);
    expect(mockProvider).toHaveBeenCalledWith(
        expect.objectContaining({
          theme: expect.objectContaining({ light: expect.anything() }),
          colorScheme: "light",
          children: expect.anything(),
        })
    );
  });

  it("uses dark theme when prefers-color-scheme is dark", () => {
    (window.matchMedia as any) = jest.fn().mockImplementation(query => ({
      matches: true, // Dark mode
      media: query,
      onchange: null,
      addListener: jest.fn(),
      removeListener: jest.fn(),
      addEventListener: jest.fn(),
      removeEventListener: jest.fn(),
      dispatchEvent: jest.fn(),
    }));

    render(<App />);
    expect(mockProvider).toHaveBeenCalledWith(
        expect.objectContaining({
          theme: expect.objectContaining({ dark: expect.anything() }),
          colorScheme: "dark",
          children: expect.anything(),
        })
    );
  });
});
